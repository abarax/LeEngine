#version 410

in vec2 texCoord0;

uniform vec3 baseColour;
uniform vec3 ambientLight;
uniform  sampler2D sampler;

out vec4 frag_color;


void main () {

    vec4 totalLight = vec4(ambientLight, 1);
    vec4 colour = vec4(baseColour, 1);
    vec4 textureColour =  texture(sampler, texCoord0.xy);

    if (textureColour == vec4(0, 0, 0, 1))
        colour *= textureColour;

    frag_color = colour * totalLight;
}