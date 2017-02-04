package com.oneuse.dainbow.web;

import com.oneuse.dainbow.Book;
import com.oneuse.dainbow.BookService;
import com.oneuse.dainbow.builders.BookBuilder;
import com.oneuse.dainbow.config.PersistenceConfig;
import com.oneuse.dainbow.config.TestConfig;
import com.oneuse.dainbow.config.WebConfig;
import com.oneuse.dainbow.exceptions.BookNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, PersistenceConfig.class, WebConfig.class})
@ActiveProfiles("test")
@WebAppConfiguration
public class BookControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private BookService bookServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(bookServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test_ListBooks_Should_AddBooksToModelAndRenderBookListView() throws Exception {
        Book book1 = new BookBuilder()
                .setTitle("title1")
                .setAuthor("author1")
                .setTotalPagesCount(100)
                .setEmptyImage()
                .build();

        Book book2 = new BookBuilder()
                .setTitle("title2")
                .setAuthor("author2")
                .setTotalPagesCount(200)
                .setEmptyImage()
                .build();

        when(bookServiceMock.findAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("bookList"))
                .andExpect(model().attribute("bookList", hasSize(2)))
                .andExpect(model().attribute("bookList", hasItem(
                        allOf(
                                hasProperty("title", equalTo("title1")),
                                hasProperty("author", equalTo("author1")),
                                hasProperty("totalPagesCount", is(100))
                        )
                )))
                .andExpect(model().attribute("bookList", hasItem(
                        allOf(
                                hasProperty("title", equalTo("title2")),
                                hasProperty("author", equalTo("author2")),
                                hasProperty("totalPagesCount", is(200))
                        )
                )));

        verify(bookServiceMock, times(1)).findAllBooks();
        verifyNoMoreInteractions(bookServiceMock);
    }

    @Test
    public void test_When_TryGetBookWithNoExistentId_Should_Render404View() throws Exception {
        when(bookServiceMock.findBook(1L)).thenThrow(BookNotFoundException.class);

        mockMvc.perform(get("/books/1")).andExpect(status().isNotFound());
    }
}
