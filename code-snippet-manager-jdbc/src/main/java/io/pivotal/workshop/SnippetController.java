package io.pivotal.workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    private final SnippetRepository snippetRepository;
    private final SnippetPresenter snippetPresenter;

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody void handleException(ParseException e) {
        // Nothing
    }

    @Autowired
    public SnippetController(SnippetRepository snippetRepository, SnippetPresenter snippetPresenter) {
        this.snippetRepository = snippetRepository;
        this.snippetPresenter = snippetPresenter;
    }

    @GetMapping
    public List<SnippetInfo> snippets(
            @RequestParam(value="start", required=false) String start,
            @RequestParam(value="end", required=false) String end
    ) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date dateStart = null;
        Date dateEnd = null;

        if (start != null) {
            dateStart = formatter.parse(start);
        }
        if (end != null) {
            dateEnd = formatter.parse(end);
        }

        return snippetRepository.findAll(dateStart, dateEnd)
                .stream()
                .map(snippetPresenter::present)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public SnippetInfo snippet(@PathVariable("id") String id) {
        SnippetRecord record = snippetRepository.findOne(id);
        return snippetPresenter.present(record);
    }

    @PostMapping
    public ResponseEntity<SnippetInfo> add(@RequestBody NewSnippetFields newSnippetFields) {
        SnippetRecord savedSnippetRecord =
                snippetRepository.save(newSnippetFields);
        SnippetInfo savedSnippetInfo =
                snippetPresenter.present(savedSnippetRecord);

        HttpHeaders httpHeaders = new HttpHeaders();


        httpHeaders.setLocation(buildSnippetUri(savedSnippetInfo));
        return new ResponseEntity<>(savedSnippetInfo, httpHeaders, HttpStatus.CREATED);
    }

    private URI buildSnippetUri(SnippetInfo snippet) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/" + snippet.id).buildAndExpand().toUri();
    }
}
