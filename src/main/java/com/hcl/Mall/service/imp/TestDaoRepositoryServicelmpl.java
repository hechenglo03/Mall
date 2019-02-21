package com.hcl.Mall.service.imp;

import com.hcl.Mall.controller.TestDao;
import com.hcl.Mall.exception.NotSubmitSuccessException;
import com.hcl.Mall.repository.TestDaoRepository;
import com.hcl.Mall.service.TestDaoRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestDaoRepositoryServicelmpl implements TestDaoRepositoryService {

    @Autowired
    private TestDaoRepository testDaoRepository;

    @Override
    public void save(TestDao testDao) {
        this.testDaoRepository.save(testDao);
    }


    @Transactional(noRollbackFor = NotSubmitSuccessException.class)
    public void update(String title) throws Exception{
        this.testDaoRepository.update(title);
        throw new NotSubmitSuccessException();
    }
}
