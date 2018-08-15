package community.flock.eco.feature.members.controllers


import community.flock.eco.feature.members.model.Member
import community.flock.eco.feature.members.model.MemberGroup
import community.flock.eco.feature.members.repositories.MemberGroupRepository
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/member_groups")
open class MemberGroupController(private val memberGroupRepository: MemberGroupRepository) {

    @GetMapping
    fun findAll(): List<MemberGroup> {
        return memberGroupRepository.findAll().toList()
    }

    @PostMapping
    fun create(@RequestBody memberGroup: MemberGroup): MemberGroup {
        return memberGroupRepository.save(memberGroup)
    }

    @PutMapping("/{code}")
    fun update(@PathVariable("code") code: String, @RequestBody memberGroup: MemberGroup): MemberGroup {
        return memberGroupRepository.save(
                memberGroup.copy(
                        code = code
                )
        )
    }

    @DeleteMapping("/{code}")
    fun delete(@PathVariable("code") code: String) {
        memberGroupRepository.deleteById(code)
    }


}