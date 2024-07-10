provider "aws" {
  region = "ap-southeast-1" # Change to your desired region
}

resource "aws_instance" "example" {
  ami           = "ami-06d753822bd94c64e" # Change to your desired AMI ID
  instance_type = "t2.micro"

  tags = {
    Name = "ExampleInstance"
  }
}

resource "aws_s3_bucket" "example" {
  bucket = "example-bucket-123456" # Change to your desired bucket name
  acl    = "private"
}

output "instance_id" {
  value = aws_instance.example.id
}

output "bucket_arn" {
  value = aws_s3_bucket.example.arn
}