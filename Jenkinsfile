pipeline {
    // Вказуємо, на якому агенті (вузлі) Jenkins буде виконуватися цей пайплайн
    agent any

    // Визначаємо інструменти, які потрібно підготувати перед запуском.
    // Цей блок автоматично додасть потрібні директорії (напр., /bin) до змінної PATH.
    tools {
        jdk 'openjdk-17'
        maven 'maven-3.9.6' // Приклад назви Maven
        allure 'allure'      // Приклад назви Allure Commandline
    }

 // Визначаємо стадії (етапи) нашого пайплайну
     stages {
         // Стадія 1: Завантаження коду з репозиторію
         stage('Checkout Code') {
             steps {
                 echo 'Завантаження коду з репозиторію...'
                 checkout scm
             }
         }

                 // Стадія 2: Збірка та тестування
                 stage('Build & Test') {
                     steps {
                         // Діагностичні кроки для перевірки, які змінні середовища встановив Jenkins
                         echo '--- Перевірка середовища ---'
                         sh 'echo "JAVA_HOME is set to: $JAVA_HOME"'
                         sh 'echo "PATH is set to: $PATH"'
                         sh 'java -version'
                         sh 'mvn -version'
                         echo '----------------------------'

                         echo 'Запуск збірки та виконання тестів...'
                         // Використовуємо 'verify', що є більш повною фазою життєвого циклу Maven
                         sh 'mvn clean verify -B -Dsurefire.suiteXmlFiles=testng.xml'
                     }
                 }
             }

     // Блок, який виконується після завершення всіх стадій
     post {
         // 'always' означає, що цей крок буде виконано незалежно від того,
         // чи були тести успішними, чи впали з помилкою.
         always {
             echo 'Генерація звіту Allure...'
             // Allure також повинен успадкувати правильне середовище, налаштоване блоком 'tools'
             allure includeProperties: false, report: 'allure-report', results: [[path: 'target/allure-results']]
         }
     }
 }
