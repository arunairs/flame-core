package cn.blinkmind.flame.gradle.external

import org.gradle.api.Project

abstract class Container extends Component {

    Container(String name, Project project) {
        super(name, project)
    }

    abstract deploy(Object artifact)

    abstract undeploy(Object artifact)
}
