package com.oneuse.dainbow.books.application.repository;

import com.oneuse.dainbow.books.application.TestConfig;
import com.oneuse.dainbow.books.application.config.ApplicationConfig;
import com.oneuse.dainbow.books.application.entity.BookEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({ApplicationConfig.class, TestConfig.class})
public class BookRepositoryTest {

    @Value("${spring.data.rest.base-path}")
    private String apiUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_return_book_when_valid_request() {
        // given
        long bookId = 1;

        // when
        ResponseEntity<BookEntity> book = restTemplate.getForEntity(getBookUrl(bookId), BookEntity.class);

        // then
        assertNotNull(book);
    }

    private String getBookUrl(long bookId) {
        return String.format("%s/books/%d", apiUrl, bookId);
    }
}
