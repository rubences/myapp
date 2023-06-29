package io.bootify.my_app.config

import java.io.File
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.thymeleaf.TemplateEngine
import org.thymeleaf.templateresolver.FileTemplateResolver


/**
 * Load Thymeleaf files from the file system during development, without any caching.
 */
@Configuration
@Profile("local")
class LocalDevConfig(
    templateEngine: TemplateEngine
) {

    init {
        var sourceRoot: File = ClassPathResource("application.yml").file.parentFile
        while (sourceRoot.listFiles { _, name -> name.equals("gradlew") } .size != 1) {
            sourceRoot = sourceRoot.parentFile
        }
        val fileTemplateResolver = FileTemplateResolver()
        fileTemplateResolver.prefix = sourceRoot.path + "/src/main/resources/templates/"
        fileTemplateResolver.suffix = ".html"
        fileTemplateResolver.setCacheable(false)
        fileTemplateResolver.characterEncoding = "UTF-8"
        fileTemplateResolver.checkExistence = true

        templateEngine.setTemplateResolver(fileTemplateResolver)
    }

}
