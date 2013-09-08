# ex: set ft=ruby :

repositories.remote << 'http://download.java.net/maven/2/'
repositories.remote << 'http://repo1.maven.org/maven2'
repositories.remote << 'https://maven.atlassian.com/public/com/atlassian/jira/jira-rest-java-client/'

MAIL=transitive('javax.mail:mail:jar:1.4.7')
IDEA_FORMS=transitive('com.intellij:forms_rt:jar:7.0.3')
IDEA_ANNOT=transitive('com.intellij:annotations:jar:12.0')
JIRA_CLIENT='com.atlassian.jira:jira-rest-java-client:jar:2.0.0-m2'

VERSION_NUMBER='0.0.0'

define 'JiraMan' do
  project.version = VERSION_NUMBER
  compile.options.target = '1.7'
  compile.options.lint = 'all'
  compile.options.source = '1.7'
  compile.with(MAIL, IDEA_FORMS, IDEA_ANNOT)
  run.using :main => 'ca.junctionbox.jiraman.ui.swing.NotificationFrame'
end
