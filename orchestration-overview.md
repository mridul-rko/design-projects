# IDP Orchestration Layer – Design Overview

This document outlines the design of an Intelligent Document Processing (IDP) Orchestration Layer built with Java Spring Boot and Kafka.

## Functional Components:
- **Client Upload API (REST)**: Accepts PDF uploads and sends metadata (doc ID, user, file path, is_vip) to the Orchestrator Service.
- **Orchestrator Service**: Coordinates processing by sending tasks to Kafka topics and tracking status.
- **Kafka Topics**:
  - **split-topic**: Triggers PDF splitting.
  - **classify-topic**: For document classification.
  - **extract-topic**: For field extraction.
- **Worker Microservices**:
  - **Split Worker**: Splits PDF into sub-documents and forwards pages.
  - **Classifier Worker**: Classifies document pages.
  - **Extractor Worker**: Extracts required data fields.
- **Status Tracker / Task Manager**: Aggregates task results and updates status in MySQL.
- **Database Layer**:
  - **MySQL**: Stores `documents`, `sub_documents`, and `task_status` data.
  - **MongoDB/Elasticsearch**: Logs errors, retries, and exceptions.
- **Blob Storage**: AWS S3 to store PDFs and extracted outputs.
- **Monitoring/Observability**: Prometheus & Grafana for metrics on queue lag, task latency, and failures.

## Non-Functional Requirements:
- Asynchronous processing via Kafka.
- VIP prioritization using message headers or separate queues.
- Auto-scaling of stateless worker services.
- Retry logic with exponential backoff and Dead-Letter Queue (DLQ).
- Idempotency in task handling.
- Fault tolerance through Kafka re-assignment and DB transaction safety.