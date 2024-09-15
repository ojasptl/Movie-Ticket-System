# Generative Adversarial Network (GAN) with PyTorch

This project implements a Generative Adversarial Network (GAN) using PyTorch. The architecture consists of two primary components:
- A **Generator** that creates images from random noise.
- A **Discriminator** that classifies images as real or fake.

## Architecture Overview
![Architecture Diagram](GAN-architecture.jpg)

## Generator: Detailed Forward Flow Overview

| Step    | Operation                                   | Shape              |
|---------|---------------------------------------------|--------------------|
| Input   | Random noise                                | (512, 32, 32)      |
| Step 1  | ConvTranspose(512 filters, $4 \times 4$)    | (512, 64, 64)      |
| Step 2  | BatchNorm, LeakyReLU                        | (512, 64, 64)      |
| Step 3  | ConvTranspose(512 filters, $4 \times 4$)    | (512, 64, 64)      |
| Step 4  | BatchNorm, LeakyReLU                        | (512, 64, 64)      |
| Step 5  | ConvTranspose(512 filters, $4 \times 4$)    | (512, 64, 64)      |
| Step 6  | BatchNorm, LeakyReLU                        | (512, 64, 64)      |
| Step 7  | ConvTranspose(256 filters, $4 \times 4$)    | (256, 64, 64)      |
| Step 8  | BatchNorm, LeakyReLU                        | (256, 64, 64)      |
| Step 9  | ConvTranspose(256 filters, $4 \times 4$)    | (256, 64, 64)      |
| Step 10 | BatchNorm, LeakyReLU                        | (256, 64, 64)      |
| Step 11 | ConvTranspose(Output, $4 \times 4$)         | (3, 64, 64)        |
| Step 12 | Tanh Activation                             | (3, 64, 64)        |

## Discriminator: Detailed Forward Flow Overview

| Step    | Operation                                   | Shape              |
|---------|---------------------------------------------|--------------------|
| Input   | Real/Fake Image                             | (3, 64, 64)        |
| Step 1  | Conv(16 filters, $4 \times 4$, LeakyReLU)   | (16, 32, 32)       |
| Step 2  | Conv(32 filters, $4 \times 4$, LeakyReLU)   | (32, 16, 16)       |
| Step 3  | BatchNorm, LeakyReLU                        | (32, 16, 16)       |
| Step 4  | Conv(64 filters, $4 \times 4$, LeakyReLU)   | (64, 8, 8)         |
| Step 5  | Conv(128 filters, $4 \times 4$, LeakyReLU)  | (128, 4, 4)        |
| Step 6  | BatchNorm, LeakyReLU                        | (128, 4, 4)        |
| Step 7  | Conv(256 filters, $4 \times 4$, LeakyReLU)  | (256, 2, 2)        |
| Step 8  | Conv(512 filters, $4 \times 4$, LeakyReLU)  | (512, 1, 1)        |
| Step 9  | Flatten, Linear(512 → 256)                  | (256)              |
| Step 10 | LeakyReLU, Linear(256 → 1)                  | Scalar (1)         |

## Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/GAN-PyTorch.git
   cd GAN-PyTorch
