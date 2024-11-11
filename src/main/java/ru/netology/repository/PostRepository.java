package ru.netology.repository;

import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    protected CopyOnWriteArrayList<Post> postsList;
    volatile protected AtomicLong counter = new AtomicLong(0);

    public List<Post> all() {
        return postsList;
    }

    public Optional<Post> getById(long id) {
        int intId = (int) id;
        return Optional.ofNullable(postsList.get(intId));
    }

    public Post save(Post post) {
        Post data = post;
        if (data.getId() == 0) {
            counter.getAndIncrement();
            data.setId(counter.get());
            postsList.add(data);
        } else {
            postsList.set((int) counter.get(), data);
        }
        return data;
    }

    public void removeById(long id) {
        postsList.remove(id);
    }
}
///переделать все методы класса