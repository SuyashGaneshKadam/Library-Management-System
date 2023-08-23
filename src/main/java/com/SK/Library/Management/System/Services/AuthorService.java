package com.SK.Library.Management.System.Services;

import com.SK.Library.Management.System.Models.*;
import com.SK.Library.Management.System.Repositories.*;
import com.SK.Library.Management.System.RequestDTOs.*;
import com.SK.Library.Management.System.ResponseDTOs.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(Author author) throws Exception{
        if(author.getAuthorId() != null){
            throw new Exception("Please don't enter Author ID");
        }
        authorRepository.save(author);
        return "Author data added successfully";
    }
    public String updateAgeAndPenName(AgeAndPenNameRequestDto requestDto) throws Exception{
        if(!authorRepository.existsById(requestDto.getAuthorId())){
            throw new Exception("Invalid Author ID");
        }
        Author author = authorRepository.findById(requestDto.getAuthorId()).get();

        author.setAge(requestDto.getAge());
        author.setPenName(requestDto.getPenName());

        authorRepository.save(author);
        return "Name and Pen name updated successfully";
    }
    public GetAuthorByIdResponseDto getAuthorDtoById(Integer authorId) throws Exception{
        if(!authorRepository.existsById(authorId)){
            throw new Exception("Invalid Author ID");
        }
        Author author = authorRepository.findById(authorId).get();
        GetAuthorByIdResponseDto responseDto = new GetAuthorByIdResponseDto(author.getName(), author.getEmailId(), author.getAge(), author.getPenName());
        return responseDto;
    }
    public Author getAuthorById(Integer authorId) throws Exception{
        if(!authorRepository.existsById(authorId)){
            throw new Exception("Invalid Author ID");
        }
        Author author = authorRepository.findById(authorId).get();
        return author;
    }
    public List<String> getBookTitlesByAuthorId(Integer authorId) throws Exception{
        if(!authorRepository.existsById(authorId)){
            throw new Exception("Invalid Author ID");
        }
        List<String> bookList = new ArrayList<>();
        for(Book book : authorRepository.findById(authorId).get().getBookList()){
            bookList.add(book.getTitle());
        }
        return bookList;
    }
}
