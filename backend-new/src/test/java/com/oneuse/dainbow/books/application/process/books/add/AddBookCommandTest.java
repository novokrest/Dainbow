package com.oneuse.dainbow.books.application.process.books.add;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.oneuse.dainbow.books.application.dao.BookDao;
import com.oneuse.dainbow.books.application.dao.BookHistoryDao;
import com.oneuse.dainbow.books.application.entity.BookEntity;
import com.oneuse.dainbow.books.application.entity.BookReadActivityEntity;
import com.oneuse.dainbow.books.application.process.dto.ReadActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class AddBookCommandTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookHistoryDao bookHistoryDao;

    @Test
    public void should_add_book_when_valid_request() throws Exception {
        // given
        AddBookRequest request = AddBookRequest.builder()
                .withTitle("Java Concurrency in Practice")
                .withAuthor("Brian Goetz")
                .withReadPagesCount(113)
                .withTotalPagesCount(551)
                .withReadActivities(ImmutableList.of(
                        new ReadActivity(10, 15),
                        new ReadActivity(21, 31)
                ))
                .build();

        // when
        MvcResult result = mockMvc
                .perform(post("/api/v2/read/books/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(MAPPER.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        // then
        String responseContent = result.getResponse().getContentAsString();
        AddBookResponse response = MAPPER.readValue(responseContent, AddBookResponse.class);
        assertNotNull(response.getBookId());

        Optional<BookEntity> foundBook = bookDao.fetchById(response.getBookId());
        assertTrue(foundBook.isPresent());

        List<BookReadActivityEntity> foundReadActivities = bookHistoryDao.fetchByBookId(response.getBookId());
        assertTrue(foundReadActivities.size() > 0);
    }
}
