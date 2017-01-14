package com.oneuse.dainbow.web;

import com.oneuse.dainbow.*;
import com.oneuse.dainbow.book.ReadHistory;
import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.image.ImageType;
import com.oneuse.dainbow.storage.BookRepository;
import com.oneuse.dainbow.storage.ReadHistoryRepository;
import com.oneuse.dainbow.web.viewmodels.LogViewModelToReadActivityConverter;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class BookControllerTest {
    @Test
    public void testBooksPage() throws Exception {
        BookController bookController = createBookController();
        MockMvc mockMvc = createSingleViewMvc(bookController);
        mockMvc.perform(get("/books"))
               .andExpect(view().name("books"));
    }

    private MockMvc createSingleViewMvc(Object controller) {
        return standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/default.jsp"))
                .build();
    }

    private BookController createBookController() {
        List<Book> books = BookUtils.createBooks(10);
        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.findAll()).thenReturn(books);

        ReadHistoryRepository readHistoryRepository = mock(ReadHistoryRepository.class);
        when(readHistoryRepository.findReadHistory(anyLong())).thenReturn(new ReadHistory(new ArrayList<>()));

        BookCoverProvider bookCoverProvider = mock(BookCoverProvider.class);
        when(bookCoverProvider.findCover(anyLong())).thenReturn(new Image(ImageType.PNG, new byte[0]));

        LogViewModelToReadActivityConverter logViewModelToReadActivityConverter = mock(LogViewModelToReadActivityConverter.class);

        return new BookController(bookRepository, readHistoryRepository, bookCoverProvider, logViewModelToReadActivityConverter);
    }
}
