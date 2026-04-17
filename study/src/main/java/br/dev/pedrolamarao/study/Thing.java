package br.dev.pedrolamarao.study;

import org.springframework.data.annotation.Id;

public record Thing(@Id Long id, String name) {
}
