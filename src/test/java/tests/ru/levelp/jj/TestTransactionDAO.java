package tests.ru.levelp.jj;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.levelp.jj.dao.TransactionsDAO;
import ru.levelp.jj.model.Transaction;
import ru.levelp.jj.model.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TestTransactionDAO implements TransactionsDAO {

    @Override
    public List<Transaction> findByUser(User user) {
        return Collections.singletonList(
                new Transaction(new Date(), 100.0, user,
                        new User("test-user", "pass")
                )
        );
    }

    @Override
    public List<Transaction> findLast(Pageable paging) {
        return null;
    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public List<Transaction> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Transaction> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public <S extends Transaction> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Transaction> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Transaction> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Transaction> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Transaction getOne(Long aLong) {
        return null;
    }

    @Override
    public Transaction getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Transaction> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Transaction> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<Transaction> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Transaction> S save(S entity) {
        return null;
    }

    @Override
    public Optional<Transaction> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Transaction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Transaction> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Transaction> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Transaction> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Transaction> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Transaction> boolean exists(Example<S> example) {
        return false;
    }
}
