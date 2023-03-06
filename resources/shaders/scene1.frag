#version 330
//biasae buat kasi warna

out vec4 fragColor;
in vec4 out_color;

void main() {
    //vec4(red,green,blue,alpha)
    //karena ndak bisa lebih dari 1, maka pewarnaan pake pecahan cth: 100/255 = ...f
    //fragColor = vec4(1.0f, 0.75f, 0.79f, 1.0f);

    fragColor = out_color;
}