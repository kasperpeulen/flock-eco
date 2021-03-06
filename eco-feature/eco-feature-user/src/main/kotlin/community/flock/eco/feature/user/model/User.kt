package community.flock.eco.feature.user.model

import community.flock.eco.core.events.EventEntityListeners
import java.io.Serializable
import javax.persistence.*

@Entity
@EntityListeners(EventEntityListeners::class)
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(unique = true)
    val reference: String,

    val name: String,
    val email: String,

    @ElementCollection(fetch = FetchType.EAGER)
    val authorities: Set<String> = setOf()

) : Serializable
