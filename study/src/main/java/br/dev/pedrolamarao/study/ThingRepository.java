package br.dev.pedrolamarao.study;

import org.springframework.data.repository.CrudRepository;

public interface ThingRepository extends CrudRepository<Thing, Long>
{
}
