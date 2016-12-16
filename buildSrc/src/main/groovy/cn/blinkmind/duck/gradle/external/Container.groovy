package cn.blinkmind.duck.gradle.external

import org.gradle.api.Project

/**
 * @author jiaan.zhang@outlook.com
 * @date 06/10/2016 12:46 AM
 */
abstract class Container extends Component {

    Container(String name, Project project) {
        super(name, project)
    }

    abstract deploy(Object artifact)

    abstract undeploy(Object artifact)
}
