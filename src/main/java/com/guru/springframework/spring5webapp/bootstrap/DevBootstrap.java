package com.guru.springframework.spring5webapp.bootstrap;

import com.guru.springframework.spring5webapp.model.Author;
import com.guru.springframework.spring5webapp.model.Book;
import com.guru.springframework.spring5webapp.model.Publisher;
import com.guru.springframework.spring5webapp.repositories.AuthorRepository;
import com.guru.springframework.spring5webapp.repositories.BookRepository;
import com.guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher imdb = new Publisher("imdb", "internet");
        Book ddd = new Book("Domain Driven Design", "1234", imdb);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(imdb);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher tvf = new Publisher("tvf", "internet");
        Book j2ee = new Book("J2EE Development without EJB", "23444", tvf);
        rod.getBooks().add(j2ee);
        j2ee.getAuthors().add(rod);

        publisherRepository.save(tvf);
        authorRepository.save(rod);
        bookRepository.save(j2ee);
    }
}
