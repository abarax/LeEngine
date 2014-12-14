#version 410

in vec2 texCoord0;
in vec3 normal0;



out vec4 frag_color;

struct BaseLight
{
    vec3 color;
    float intensity;
};

struct DirectionalLight
{
    BaseLight base;
    vec3 direction;
};

vec4 calcLight(BaseLight base, vec3 direction, vec3 normal) {
   float diffuseFactor = dot(normal, -direction);

   vec4 diffuseColor = vec4(0,0,0,0);

   if(diffuseFactor > 0) {
        diffuseColor = vec4(base.color, 1.0) * base.intensity * diffuseFactor;
   }

   return diffuseColor;
}

vec4 calcDirectionalLight(DirectionalLight directionalLight, vec3 normal) {
    return calcLight(directionalLight.base, -directionalLight.direction, normal);
}

uniform vec3 baseColour;
uniform vec3 ambientLight;
uniform  sampler2D sampler;

uniform DirectionalLight directionalLight;


void main () {

    vec4 totalLight = vec4(ambientLight, 1);
    vec4 colour = vec4(baseColour, 1);
    vec4 textureColour =  texture(sampler, texCoord0.xy);

    if (textureColour == vec4(0, 0, 0, 1))
        colour *= textureColour;

    vec3 normal = normalize(normal0);

    totalLight += calcDirectionalLight(directionalLight, normal);

    frag_color = colour * totalLight;
}