package com.oneuse.dainbow.web;

import com.oneuse.dainbow.Book;
import com.oneuse.dainbow.BookDTO;
import com.oneuse.dainbow.BookService;
import com.oneuse.dainbow.book.ReadHistory;
import com.oneuse.dainbow.builders.BookBuilder;
import com.oneuse.dainbow.builders.ReadHistoryBuilder;
import com.oneuse.dainbow.config.PersistenceConfig;
import com.oneuse.dainbow.config.TestConfig;
import com.oneuse.dainbow.config.WebConfig;
import com.oneuse.dainbow.exceptions.BookNotFoundException;
import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.image.ImageType;
import com.oneuse.dainbow.web.viewmodels.LogViewModel;
import com.oneuse.dainbow.web.viewmodels.RegisterBookViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .setCoverImage(Image.empty(ImageType.JPEG))
                .build();

        Book book2 = new BookBuilder()
                .setTitle("title2")
                .setAuthor("author2")
                .setTotalPagesCount(200)
                .setCoverImage(Image.empty(ImageType.JPEG))
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

    @Test
    public void test_When_GetBookId_Then_BookFound_Should_RenderBookView() throws Exception {
        Book book = new BookBuilder()
                .setTitle("book1")
                .setAuthor("author1")
                .setTotalPagesCount(100)
                .build();

        ReadHistory history = new ReadHistoryBuilder()
                .addReadActivity()
                .addPages("1 - 10")
                .setDayPeriod("2012-12-12", "11:00:00", "12:00:00")
                .addReadActivity()
                .addPages("100 - 200")
                .setDayPeriod("2012-11-11", "11:00:00", "12:00:00")
                .build();


        when(bookServiceMock.findBook(1L)).thenReturn(book);
        when(bookServiceMock.findBookReadHistory(1L)).thenReturn(history);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("readSummary"))
                .andExpect(model().attributeExists("log"))
                .andExpect(model().attributeExists("history"));
    }

    @Test
    public void test_LogReadActivity_Should_AddReadActivityAndRenderBookView() throws Exception {
        mockMvc.perform(post("/books/1/log")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("pages", "1 - 10")
                .param("day", "2012-12-12")
                .param("beginTime", "11:00")
                .param("endTime", "12:00")
                .sessionAttr("log", new LogViewModel())
        )
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books/{bookId}"))
                .andExpect(redirectedUrl("/books/1"))
                .andExpect(model().attribute("bookId", is(1L)));

        ArgumentCaptor<LogViewModel> formValue = ArgumentCaptor.forClass(LogViewModel.class);
        verify(bookServiceMock, times(1)).logReadActivity(anyLong(), formValue.capture());
        verifyNoMoreInteractions(bookServiceMock);

        LogViewModel log = formValue.getValue();

        Assert.assertThat(log.getDay(), is(LocalDate.parse("2012-12-12")));
        Assert.assertThat(log.getBeginTime(), is(LocalTime.parse("11:00")));
        Assert.assertThat(log.getEndTime(), is(LocalTime.parse("12:00")));
        Assert.assertThat(log.getPages(), hasProperty("pagesCount", is(10)));
    }

    @Test
    public void test_GetRegisterNewBookView_Should_ReturnRegisterView() throws Exception {
        mockMvc.perform(get("/books/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("viewModel"));
    }

    @Test
    public void test_TryRegisterNewValidBookWithoutCover_Should_AddBookAndRenderBookView() throws Exception {
        Book addedBook = new BookBuilder()
                .setId(1L)
                .setTitle("title1")
                .setAuthor("author1")
                .setTotalPagesCount(100)
                .build();

        when(bookServiceMock.addBook(isA(BookDTO.class))).thenReturn(addedBook);

        mockMvc.perform(post("/books/register")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("title", "title1")
                .param("author", "author1")
                .param("totalPagesCount", "100")
                .sessionAttr("viewModel", new RegisterBookViewModel())
            )
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books/{bookId}"))
                .andExpect(redirectedUrl("/books/1"))
                .andExpect(model().attribute("bookId", is("1")));

        ArgumentCaptor<RegisterBookViewModel> captor = ArgumentCaptor.forClass(RegisterBookViewModel.class);
        verify(bookServiceMock, times(1)).addBook(captor.capture());

        RegisterBookViewModel viewModel = captor.getValue();
        Assert.assertThat(viewModel.getTitle(), equalTo("title1"));
        Assert.assertThat(viewModel.getAuthor(), equalTo("author1"));
        Assert.assertThat(viewModel.getTotalPagesCount(), equalTo(100));
    }

    @Test
    public void test_TryRegisterBookWithInvalidData_Should_RenderRegisterBookViewAndReturnValidationErrors() throws Exception {
        mockMvc.perform(post("/books/register")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("title", "")
                .param("author", "")
                .param("totalPagesCount", "-100")
                .sessionAttr("viewModel", new RegisterBookViewModel())
            )
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("viewModel"))
                .andExpect(model().attribute("viewModel", hasProperty("title", isEmptyOrNullString())))
                .andExpect(model().attribute("viewModel", hasProperty("author", isEmptyOrNullString())))
                .andExpect(model().attribute("viewModel", hasProperty("totalPagesCount", is(-100))))
                .andExpect(model().attributeHasFieldErrors("viewModel", "title", "author", "totalPagesCount"));

        verifyZeroInteractions(bookServiceMock);
    }
}
