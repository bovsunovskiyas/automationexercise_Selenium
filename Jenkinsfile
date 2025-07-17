// Декларативний Pipeline
pipeline {
    // Вказуємо, на якому агенті (вузлі) Jenkins буде виконуватися цей пайплайн
    agent any

    // Визначаємо інструменти, які потрібно підготувати перед запуском
    // Важливо: назви 'jdk-24' та 'Allure' повинні відповідати тим, що налаштовані
    // у вашому Jenkins -> Manage Jenkins -> Tools
    tools {
        jdk 'jdk-24'
        maven 'maven-3.9.6' // Приклад назви Maven
        allure 'Allure'      // Приклад назви Allure Commandline
    }

    // Визначаємо стадії (етапи) нашого пайплайну
    stages {
        // Стадія 1: Завантаження коду з репозиторію
        stage('Checkout Code') {
            steps {
                echo 'Завантаження коду з репозиторію...'
                // Використовуємо стандартний крок для завантаження коду
                // з SCM, налаштованого в джобі
                checkout scm
            }
        }

        // Стадія 2: Компіляція та запуск тестів
        stage('Build & Test') {
            steps {
                echo 'Запуск компіляції та виконання тестів...'
                // Використовуємо Maven для очищення, компіляції та запуску тестів.
                // Maven автоматично згенерує результати для Allure у папку target/allure-results
                sh 'mvn -B test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }
    }

    // Блок, який виконується після завершення всіх стадій
    post {
        // 'always' означає, що цей крок буде виконано незалежно від того,
        // чи були тести успішними, чи впали з помилкою.
        always {
            echo 'Генерація звіту Allure...'
            // Використовуємо плагін Allure для генерації HTML-звіту
            allure includeProperties: false, report: 'allure-report', results: [[path: 'target/allure-results']]
        }
    }
}