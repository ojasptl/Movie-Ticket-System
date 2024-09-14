\documentclass[a4paper,tikz,border=10pt]{standalone}
\usepackage{amsmath}
\usepackage{graphicx}

\begin{document}
\begin{tikzpicture}[scale=0.8, transform shape]

% Encoder
% Input Layer
\node[draw, align=center] (input) at (0,0) {Input\\ (256x256x3)};

% Conv layers + LeakyReLU for Encoder
\node[draw, align=center] (conv1) at (3,0) {Conv 16\\(3x3, LeakyReLU)\\(256x256x16)};
\node[draw, align=center] (conv2) at (6,0) {Conv 16\\(3x3, LeakyReLU)\\(256x256x16)};
\node[draw, align=center] (pool1) at (9,0) {MaxPool\\(2x2)\\(128x128x16)};

\node[draw, align=center] (conv3) at (0,-3) {Conv 32\\(3x3, LeakyReLU)\\(128x128x32)};
\node[draw, align=center] (conv4) at (3,-3) {Conv 32\\(3x3, LeakyReLU)\\(128x128x32)};
\node[draw, align=center] (pool2) at (6,-3) {MaxPool\\(2x2)\\(64x64x32)};

\node[draw, align=center] (conv5) at (9,-6) {Conv 64\\(3x3, LeakyReLU)\\(64x64x64)};
\node[draw, align=center] (conv6) at (12,-6) {Conv 64\\(3x3, LeakyReLU)\\(64x64x64)};
\node[draw, align=center] (pool3) at (15,-6) {MaxPool\\(2x2)\\(32x32x64)};

\node[draw, align=center] (conv7) at (18,-9) {Conv 128\\(3x3, LeakyReLU)\\(32x32x128)};
\node[draw, align=center] (conv8) at (21,-9) {Conv 128\\(3x3, LeakyReLU)\\(32x32x128)};
\node[draw, align=center] (pool4) at (24,-9) {MaxPool\\(2x2)\\(16x16x128)};

\node[draw, align=center] (conv9) at (27,-12) {Conv 256\\(3x3, LeakyReLU)\\(16x16x256)};
\node[draw, align=center] (conv10) at (30,-12) {Conv 256\\(3x3, LeakyReLU)\\(16x16x256)};
\node[draw, align=center] (pool5) at (33,-12) {MaxPool\\(2x2)\\(8x8x256)};
\node[draw, align=center] (latent) at (36,-12) {Latent\\(4x4x256)};

% Decoder (ConvTranspose layers)
\node[draw, align=center] (deconv1) at (36,-18) {ConvT 256\\(3x3)\\(8x8x256)};
\node[draw, align=center] (convT1_1) at (33,-18) {Conv 256\\(3x3, ReLU)\\(8x8x256)};
\node[draw, align=center] (convT1_2) at (30,-18) {Conv 256\\(3x3, ReLU)\\(8x8x256)};

\node[draw, align=center] (deconv2) at (27,-21) {ConvT 128\\(3x3)\\(16x16x128)};
\node[draw, align=center] (convT2_1) at (24,-21) {Conv 128\\(3x3, ReLU)\\(16x16x128)};
\node[draw, align=center] (convT2_2) at (21,-21) {Conv 128\\(3x3, ReLU)\\(16x16x128)};

\node[draw, align=center] (deconv3) at (18,-24) {ConvT 64\\(3x3)\\(32x32x64)};
\node[draw, align=center] (convT3_1) at (15,-24) {Conv 64\\(3x3, ReLU)\\(32x32x64)};
\node[draw, align=center] (convT3_2) at (12,-24) {Conv 64\\(3x3, ReLU)\\(32x32x64)};

\node[draw, align=center] (deconv4) at (9,-27) {ConvT 32\\(3x3)\\(64x64x32)};
\node[draw, align=center] (convT4_1) at (6,-27) {Conv 32\\(3x3, ReLU)\\(64x64x32)};
\node[draw, align=center] (convT4_2) at (3,-27) {Conv 32\\(3x3, ReLU)\\(64x64x32)};

\node[draw, align=center] (deconv5) at (0,-30) {ConvT 16\\(3x3)\\(128x128x16)};
\node[draw, align=center] (convT5_1) at (-3,-30) {Conv 16\\(3x3, ReLU)\\(128x128x16)};
\node[draw, align=center] (convT5_2) at (-6,-30) {Conv 16\\(3x3, ReLU)\\(128x128x16)};

\node[draw, align=center] (deconv6) at (-9,-33) {ConvT 3\\(3x3)\\(256x256x3)};
\node[draw, align=center] (convT6_1) at (-12,-33) {Conv 3\\(3x3, ReLU)\\(256x256x3)};
\node[draw, align=center] (convT6_2) at (-15,-33) {Conv 3\\(3x3, ReLU)\\(256x256x3)};

% Output layer
\node[draw, align=center] (output) at (-18,-33) {Output\\(256x256x3)};

% Arrows connecting blocks in Encoder
\foreach \i/\j in {input/conv1, conv1/conv2, conv2/pool1, pool1/conv3, conv3/conv4, conv4/pool2,
                   pool2/conv5, conv5/conv6, conv6/pool3, pool3/conv7, conv7/conv8, conv8/pool4,
                   pool4/conv9, conv9/conv10, conv10/pool5, pool5/latent} {
    \draw[->] (\i) -- (\j);
}

% Arrows connecting blocks in Decoder
\foreach \i/\j in {latent/deconv1, deconv1/convT1_1, convT1_1/convT1_2, convT1_2/deconv2, deconv2/convT2_1, convT2_1/convT2_2,
                   convT2_2/deconv3, deconv3/convT3_1, convT3_1/convT3_2, convT3_2/deconv4, deconv4/convT4_1, convT4_1/convT4_2,
                   convT4_2/deconv5, deconv5/convT5_1, convT5_1/convT5_2, convT5_2/deconv6, deconv6/convT6_1, convT6_1/convT6_2,
                   convT6_2/output} {
    \draw[->] (\i) -- (\j);
}

% Skip connections from Encoder to Decoder
\draw[->,dashed] (pool5.south) -- (deconv1.north);
\draw[->,dashed] (pool4.south) -- (deconv2.north);
\draw[->,dashed] (pool3.south) -- (deconv3.north);
\draw[->,dashed] (pool2.south) -- (deconv4.north);
\draw[->,dashed] (pool1.south) -- (deconv5.north);
\draw[->,dashed] (input.south) -- (deconv6.north);

\end{tikzpicture}
\end{document}
