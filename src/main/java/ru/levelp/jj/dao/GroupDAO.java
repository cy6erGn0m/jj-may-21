package ru.levelp.jj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelp.jj.model.Group;

import java.util.List;

@Repository
@RepositoryRestResource(
        collectionResourceRel = "groups",
        itemResourceRel = "group"
)
public interface GroupDAO extends JpaRepository<Group, Integer> {
    @Transactional
    default Group create(String groupName) {
        Group group = new Group(groupName);
        save(group);
        return group;
    }

    Group findByName(String name);

    @Query("from Group g where size(g.users) >= :min")
    List<Group> findBigGroups(@Param("min") int minMembersCount);
}
