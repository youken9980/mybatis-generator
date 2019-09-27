package mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.TableConfiguration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 杨剑
 * @date 2019-09-25
 */
public class LombokPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		//添加domain的import
		topLevelClass.addImportedType("lombok.Data");
		topLevelClass.addImportedType("lombok.Builder");
		topLevelClass.addImportedType("lombok.NoArgsConstructor");
		topLevelClass.addImportedType("lombok.AllArgsConstructor");
		topLevelClass.addImportedType("lombok.experimental.Accessors");

		//添加domain的注解
		topLevelClass.addAnnotation("@Data");
		topLevelClass.addAnnotation("@Builder");
		topLevelClass.addAnnotation("@NoArgsConstructor");
		topLevelClass.addAnnotation("@AllArgsConstructor");
		topLevelClass.addAnnotation("@Accessors(chain = true)");

		//添加domain的注释
		addJavaDocLine(topLevelClass);

		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		//Mapper文件的注释
		addJavaDocLine(interfaze);
		return true;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		//不生成getter
		return false;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		//不生成setter
		return false;
	}

	/**
	 * 添加文件注释
	 * @param element
	 */
	private void addJavaDocLine(JavaElement element) {
		element.addJavaDocLine("/**");

		// 获取表中文描述，xml文件中table标签属性key为desc的value值
		List<TableConfiguration> list = context.getTableConfigurations();
		if (!CollectionUtils.isEmpty(list)) {
			String desc = list.get(0).getProperty("desc");
			if (StringUtils.hasText(desc)) {
				element.addJavaDocLine(" * " + desc);
			}
		}

		// 作者信息
		String author = context.getProperty("author");
		if (StringUtils.hasText(author)) {
			element.addJavaDocLine(" * @author " + author);
		}

		// 日期格式
		String pattern = context.getProperty("pattern");
		if (StringUtils.hasText(pattern)) {
			element.addJavaDocLine(" * @date " + new SimpleDateFormat(pattern).format(new Date()));
		}
		element.addJavaDocLine(" */");
	}
}
