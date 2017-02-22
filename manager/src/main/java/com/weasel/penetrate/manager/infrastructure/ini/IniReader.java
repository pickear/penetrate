package com.weasel.penetrate.manager.infrastructure.ini;

import com.google.common.io.Resources;
import com.weasel.penetrate.manager.infrastructure.ini.section.ListSection;
import com.weasel.penetrate.manager.infrastructure.ini.section.OneSection;
import org.ini4j.Profile;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

/**
 * @author Dylan
 * @date 2017/1/18.
 */
public class IniReader extends IniHandler{

    private IniReader(){}

    public static IniReader create(){
        return new IniReader();
    }

    public IniReader load(String file) throws IOException {
        URL url = Resources.getResource(file);
        ini.load(url);
        return this;
    }

    public IniReader load(URL url) throws IOException {
        ini.load(url);
        return this;
    }

    public OneSection readOne(String sectionName){

        Assert.hasText(sectionName,"sectionName can not be empty");

        Profile.Section section = ini.get(sectionName);

        Assert.notNull(section,"no section name ["+sectionName+"] found");
        return new OneSection(section);
    }


    public ListSection readList(String sectionName){

        Assert.hasText(sectionName,"sectionName can not be empty");

        List<Profile.Section> sections = ini.getAll(sectionName);
        Assert.notEmpty(sections,"no section name ["+sectionName+"] found");
        return new ListSection(sections);
    }

    public ListSection readRegular(String regular){

        Pattern pattern = Pattern.compile(regular);

        Set<Map.Entry<String, Profile.Section>> set = ini.entrySet();

        List<Profile.Section> sections = set.stream()
                                            .filter(entry -> pattern.matcher(entry.getKey()).find())
                                            .map(entry -> entry.getValue())
                                            .collect(toList());
        return new ListSection(sections);
    }
}
