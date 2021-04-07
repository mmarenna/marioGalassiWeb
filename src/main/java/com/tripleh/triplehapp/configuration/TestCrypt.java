package com.tripleh.triplehapp.configuration;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {
// CON ESTE GENERO LAS CONTRASEÑAS
	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder(); 
		System.out.println(pe.encode("triplehgeneral"));
		
		Date date1= new Date();
		Date date2= new Date();
		
		System.out.println(getDateDiff(date1,date2,TimeUnit.NANOSECONDS));
	}
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}
