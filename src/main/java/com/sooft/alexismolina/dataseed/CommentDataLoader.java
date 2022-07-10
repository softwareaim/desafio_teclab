package com.sooft.alexismolina.dataseed;

import com.sooft.alexismolina.domain.model.Comment;
import com.sooft.alexismolina.domain.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommentDataLoader implements CommandLineRunner {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {
        loadTicketData();
    }

    private void loadTicketData() {

        if (commentRepository.count() == 0) {
            this.commentRepository.save(new Comment("the problem was solved with a high cost"));
            this.commentRepository.save(new Comment("call the fire department"));
            this.commentRepository.save(new Comment("read another not so complex book"));
            this.commentRepository.save(new Comment("the monitor was sent to be fixed"));
            this.commentRepository.save(new Comment("already passed to the corresponding department"));
            this.commentRepository.save(new Comment("pass the details of the problems"));
        }
    }


}

