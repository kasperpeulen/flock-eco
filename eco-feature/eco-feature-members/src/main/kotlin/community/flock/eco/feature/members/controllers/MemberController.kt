package community.flock.eco.feature.members.controllers


import community.flock.eco.feature.members.model.Member
import community.flock.eco.feature.members.repositories.MemberRepository
import org.springframework.web.bind.annotation.*
import java.util.*


//@RestController
@RequestMapping("/api/members")
open class MemberController(private val memberRepository: MemberRepository) {

    @GetMapping
    fun findAll(): List<Member> {
        return memberRepository.findAll().toList()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): Optional<Member> {
        return memberRepository.findById(id.toLong())
    }

    @GetMapping("/{ids}")
    fun findByIds(@PathVariable("ids") ids: List<String>): List<Member> {
        return memberRepository.findByIds(ids.map { it.toLong() })
    }

    @PostMapping()
    fun create(@RequestBody member: Member): Member {
        return memberRepository.save(member)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody member: Member): Member {
        return memberRepository.save(
                member.copy(
                        id = id.toLong()
                )
        )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String) {
        memberRepository.deleteById(id.toLong())
    }


}