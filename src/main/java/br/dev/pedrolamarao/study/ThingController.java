package br.dev.pedrolamarao.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ThingController
{
    private final Logger logger = LoggerFactory.getLogger(ThingController.class);

    private final ThingRepository things;

    public ThingController(ThingRepository things)
    {
        this.things = things;
    }

    @PostMapping(value = "/",consumes = "application/json")
    public ResponseEntity<Thing> create(@RequestBody Thing thing)
    {
        if (thing.id() != null) {
            return ResponseEntity.badRequest().build();
        }
        logger.atInfo().log("create: "+thing);
        thing = things.save(thing);
        return ResponseEntity.status(HttpStatus.CREATED).body(thing);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Thing> retrieve(@PathVariable Long id)
    {
        var thing = things.findById(id);
        logger.atInfo().log("retrieve: "+thing.orElse(null));
        return thing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Thing> update(@PathVariable Long id, @RequestBody Thing thing)
    {
        if (!Objects.equals(thing.id(), id)) {
            return ResponseEntity.badRequest().build();
        }
        thing = things.save(thing);
        logger.atInfo().log("update: "+thing);
        return ResponseEntity.status(HttpStatus.OK).body(thing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        things.deleteById(id);
        logger.atInfo().log("delete: "+id);
    }

    @GetMapping("/")
    public List<Thing> list()
    {
        var list = new ArrayList<Thing>();
        for (var marker : things.findAll())
            list.add(marker);
        logger.atInfo().log("list: "+list);
        return list;
    }
}
