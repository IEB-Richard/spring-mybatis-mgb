package tk.mybatis.simple;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {
    public static void main(String[] args) throws Exception {
        // define a list to collect the warning messages during the code generation 
    	// process.
        List<String> warnings = new ArrayList<String>();
        // when duplicates occurs, overwrite the old ones
        boolean overwrite = true;
        // Read the generator configuration file
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig-mac.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        // close the input stream
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        // Create MyBatisGenerator
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // Everything is ready, let's generate
        myBatisGenerator.generate(null);
        // Output the possible warning messages
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
