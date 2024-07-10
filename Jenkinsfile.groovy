pipeline {
    agent any

    environment {
        AWS_ACCESS_KEY_ID     = credentials('aws-access-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
        TF_VAR_region         = 'ap-southeast-1'
        TF_VAR_ami            = 'ami-06d753822bd94c64e'
        TF_VAR_instance_type  = 't2.micro'
        TF_VAR_bucket_name    = 'example-bucket-123456'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/GUNADEVELOPER/task1.git'
            }
        }

        stage('Terraform Init') {
            steps {
                sh 'terraform init'
            }
        }

        stage('Terraform Plan') {
            steps {
                sh 'terraform plan -out=tfplan'
            }
        }

        stage('Terraform Apply') {
            steps {
                sh 'terraform apply tfplan'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}