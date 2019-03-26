package com.company.project.mybatisgenerator.plugins;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行此方法生成mybatis代码
 * 生成代码自动放入对应目录
 * 配置文件targetProject应从项目名称开始到要生成到的classpath
 *
 * @date 2019/1/30 10:11
 */
public class MyBatisGeneratorRun {

	private static final Logger logger = LoggerFactory.getLogger(MyBatisGeneratorRun.class);

//	public static void main(String[] args) throws Exception {
//		MyBatisGeneratorRun app = new MyBatisGeneratorRun();
//		System.out.println(app.getClass().getResource("/").getPath());
//		app.generator();
//		System.out.println(System.getProperty("user.dir"));
//	}

	public void generator() throws Exception {
		List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(resourceAsStream);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			logger.info(warning);
		}
	}
}
