package com.oneuse.dainbow.web.viewmodels;

import com.oneuse.core.Converter;
import com.oneuse.core.time.DayPeriod;
import com.oneuse.dainbow.ReadActivity;
import org.springframework.stereotype.Component;

@Component
public class LogViewModelToReadActivityConverter implements Converter<LogViewModel, ReadActivity> {
    @Override
    public ReadActivity convert(LogViewModel logViewModel) {
        DayPeriod period = new DayPeriod(logViewModel.getDay(),
                                         logViewModel.getBeginTime(),
                                         logViewModel.getEndTime());
        return new ReadActivity(logViewModel.getPages(), period);
    }
}
