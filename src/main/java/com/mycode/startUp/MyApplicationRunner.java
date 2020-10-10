package com.mycode.startUp;

import com.mycode.edu.EduDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class MyApplicationRunner implements ApplicationRunner {

	public static final Logger log = LoggerFactory.getLogger(MyApplicationRunner.class);

	@Autowired
	private WebApplicationContext applicationContext;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		EduDataSource eduDataSource = applicationContext.getBean(EduDataSource.class);
		String eduDatasourceInfo = eduDataSource.getEduDatasourceInfo();
		log.info(eduDatasourceInfo);
	}

}
