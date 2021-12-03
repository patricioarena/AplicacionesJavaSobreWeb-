import React, { useState } from "react";
import '@hassanmojab/react-modern-calendar-datepicker/lib/DatePicker.css';
import DatePicker, { utils }  from '@hassanmojab/react-modern-calendar-datepicker';

import "./Calendar.css";


const Calendar = (props) => {

    const placeholder = `${utils().getToday().year}/${utils().getToday().month}/${utils().getToday().day}`

    const minimumDate = {
        year: utils().getToday().year,
        month: utils().getToday().month,
        day: utils().getToday().day + 5
      };

    return (
        <DatePicker
            value={props.value}
            onChange={props.onChange}
            colorPrimary="#007bff"
            inputPlaceholder={placeholder}
            inputClassName="form-control"
            calendarClassName="custom-calendar responsive-calendar"
            calendarTodayClassName="custom-today-day"
            minimumDate={minimumDate}
            shouldHighlightWeekends
        />
    );
};

export default Calendar;