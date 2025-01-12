package com.in4c.blogApp.service;

import com.in4c.blogApp.model.Hashtag;
import com.in4c.blogApp.model.helper.Result;
import com.in4c.blogApp.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HashtagService {
    @Autowired
    private HashtagRepository hashTagRepo;

    public Result<Hashtag> createHashTag(Hashtag obj) {
        if (obj.getTag() == null) {
            return new Result<>(false, null, "Error: tag is null");
        }
        // if (hashTagRepo.existsByTagIgnoreCase(obj.getTag()) != 0) {

        Hashtag tag = hashTagRepo.findByTagIgnoreCase(obj.getTag());
        if (tag != null) {
            System.out.println(tag.toString());
            return new Result<Hashtag>(true, tag, "Success: tag is already created");

        }
        // }
        // if (tag.isPresent()) {
        // return new Result<Hashtag>(true, tag.get(), "Success: tag is already
        // created");
        // }
        hashTagRepo.save(obj);
        return new Result<>(true, null, "Success: tag is created");
    }

    public Result<Hashtag> findById(long id) {
        Optional<Hashtag> tag = hashTagRepo.findById(id);
        return tag.map(
                hashTag -> new Result<>(true, hashTag, null)).orElseGet(
                        () -> new Result<>(false, null, null));
    }
}
