package tan

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import com.tan.User
import org.springframework.web.context.request.RequestContextHolder


class CustomLoginService implements GrailsUserDetailsService {

    static transactional = true

    def serviceMethod() {

    }

    static final List NO_ROLES =
        [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]
    UserDetails loadUserByUsername(String username, boolean loadRoles)
    throws UsernameNotFoundException {
        return loadUserByUsername(username)
    }

    UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {

        User.withTransaction { status ->
            // params.j_agncyCode


            GrailsWebRequest request = RequestContextHolder.currentRequestAttributes()

           // User user = User.findByUsernameAndAgency(username)
            def user =User.findByUsername(username)

            if (!user) {
                throw new UsernameNotFoundException('User not found',
                        username)
            }

            def authorities = user.authorities.collect {
                new GrantedAuthorityImpl(it.authority)
            }

            GrailsHttpSession session = request.session
            session.user = user
            session.role = user.getRole()
            return new GrailsUser(user.username,
                    user.password,
                    user.enabled,
                    !user.accountExpired,
                    !user.passwordExpired,
                    !user.accountLocked,
                    authorities ?: NO_ROLES,
                    user.id)

        }
    }

}

