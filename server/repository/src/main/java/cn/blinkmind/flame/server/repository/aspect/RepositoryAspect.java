package cn.blinkmind.flame.server.repository.aspect;

import cn.blinkmind.flame.server.repository.entity.BasicEntity;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class RepositoryAspect
{
    @Before(
            value = "(execution(* cn.blinkmind.flame.server.repository.AbstractMongoRepository.update(cn.blinkmind.flame.server.repository.entity.BasicEntity)) && args(entity)) ||" +
                    "(execution(* cn.blinkmind.flame.server.repository.AbstractMongoRepository.update(org.springframework.data.mongodb.core.query.Query,cn.blinkmind.flame.server.repository.entity.BasicEntity)) && args(..,entity))"
    )
    public void beforeUpdate(BasicEntity<Long> entity)
    {
        if (entity == null) return;
        entity.refreshUpdatedDate();
    }

    @Before(value = "(execution(* cn.blinkmind.flame.server.repository.AbstractMongoRepository.update(org.springframework.data.mongodb.core.query.Query,org.springframework.data.mongodb.core.query.Update)) && args(update))")
    public void beforeUpdate(Update update)
    {
        if (update == null) return;
        update.set("updatedDate", new Date());
    }

    @Before(value = "execution(* cn.blinkmind.flame.server.repository.AbstractMongoRepository.insert(cn.blinkmind.flame.server.repository.entity.BasicEntity)) && args(entity)")
    public void beforeInsert(BasicEntity<Long> entity)
    {
        if (entity == null) return;
        if (entity.getCreatedDate() == null)
            entity.refreshCreatedDate();
    }
}
