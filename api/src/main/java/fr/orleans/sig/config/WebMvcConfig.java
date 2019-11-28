package fr.orleans.sig.config;

import fr.orleans.sig.model.sig.Equipement;
import fr.orleans.sig.model.sig.Saison;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }

    //Convertisseur pour l'enum saison
    public class StringToEnumConverter implements Converter<String, Saison> {
        @Override
        public Saison convert(String source) {
            try{
                //pour gerer les accents de la saison été
                String clean = StringUtils.stripAccents(source);
                return Saison.valueOf(clean.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    //Convertisseur pour l'enum equipement
    public class StringToEquipementConverter implements Converter<String, Equipement> {
        @Override
        public Equipement convert(String source) {
            try{
                //pour gerer les accents de la saison été
                String clean = StringUtils.stripAccents(source);
                return Equipement.valueOf(clean.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
        registry.addConverter(new StringToEquipementConverter());
    }
}

