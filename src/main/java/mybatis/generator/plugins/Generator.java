package mybatis.generator.plugins;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨剑
 * @date 2019-09-27
 */
@Slf4j
public class Generator {

	private static final String FILE_PATH = "generator/generatorConfig.xml";
	private static final Boolean OVER_WRITE = Boolean.TRUE;

	private void generate() {
		List<String> warnings = new ArrayList<>();
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(FILE_PATH)) {
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(is);
			DefaultShellCallback callback = new DefaultShellCallback(OVER_WRITE);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			for (String warning : warnings) {
				log.info(warning);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		new Generator().generate();
	}
}
