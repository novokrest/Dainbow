package com.oneuse.dainbow.web;

import com.oneuse.dainbow.Book;
import com.oneuse.dainbow.BookCoverProvider;
import com.oneuse.dainbow.storage.BookRepository;
import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.storage.ReadHistoryRepository;
import com.oneuse.dainbow.web.extension.PartToImageConverter;
import com.oneuse.dainbow.web.viewmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;
    private final ReadHistoryRepository readHistoryRepository;
    private final BookCoverProvider bookCoverProvider;
    private final LogViewModelToReadActivityConverter logViewModelToReadActivityConverter;

    @Autowired
    public BookController(BookRepository bookRepository,
                          ReadHistoryRepository readHistoryRepository,
                          BookCoverProvider bookCoverProvider,
                          LogViewModelToReadActivityConverter logViewModelToReadActivityConverter) {
        this.bookRepository = bookRepository;
        this.readHistoryRepository = readHistoryRepository;
        this.bookCoverProvider = bookCoverProvider;
        this.logViewModelToReadActivityConverter = logViewModelToReadActivityConverter;
    }

    @GetMapping
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{bookId}")
    public String showBook(@PathVariable long bookId, Model model) {
        if (!model.containsAttribute("book")) {
            Book book = bookRepository.findOne(bookId);
            model.addAttribute("book", BookViewModel.createFrom(book));
        }

        BookReadSummaryViewModel readSummary = BookReadSummaryViewModel.create(readHistoryRepository, bookId);
        model.addAttribute("readSummary", readSummary);

        LogViewModel log = LogViewModel.forToday();
        model.addAttribute("log", log);

        ReadHistoryViewModel history = ReadHistoryViewModel.create(readHistoryRepository, bookId);
        model.addAttribute("history", history);

        return "book";
    }

    @RequestMapping(value = "/{bookId}/cover", method = RequestMethod.GET)
    public void bookCover(@PathVariable long bookId, HttpServletResponse response) throws Exception {
        Image bookCover = bookCoverProvider.findCover(bookId);
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType(bookCover.getMimeType());
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(bookCover.getData());
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @PostMapping("/{bookId}/log")
    public String log(@PathVariable long bookId,
                      @ModelAttribute("log") @Valid LogViewModel log,
                      Model model) throws Exception {
        bookRepository.addBookPages(bookId, log.getPages());
        readHistoryRepository.logReadActivity(bookId, logViewModelToReadActivityConverter.convert(log));
        model.addAttribute("bookId", bookId);
        return "redirect:/books/{bookId}";
    }

    @GetMapping("/register")
    public String registerBookForm(Model model) {
        model.addAttribute("viewModel", new RegisterBookViewModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerBookSubmit(@RequestPart("coverImage") Part coverPart,
                                     @ModelAttribute("viewModel") @Valid RegisterBookViewModel viewModel,
                                     BindingResult bindingResult, RedirectAttributes model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("viewModel", viewModel);
            return "register";
        }

        Image coverImage = PartToImageConverter.convert(coverPart);
        Book registeredBook = Book.createNewBook(viewModel.getTitle(), viewModel.getAuthor(), viewModel.getTotalPagesCount(), coverImage);
        registeredBook = bookRepository.addBook(registeredBook);
        model.addAttribute("bookId", registeredBook.getId());
        model.addFlashAttribute("book", BookViewModel.createFrom(registeredBook));

        return "redirect:/books/{bookId}";
    }
}
