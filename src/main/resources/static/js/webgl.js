function drawCanvas() {
  const canvas = document.querySelector("#glCanvas");

  var camera, scene, renderer;
  var geometry, material, mesh;


  camera = new THREE.PerspectiveCamera(70, 600 / 400, 0.01, 10);
  camera.position.z = 600;

  scene = new THREE.Scene();

  geometry = new THREE.BoxGeometry(200, 200, 200);
  material = new THREE.MeshNormalMaterial();

  mesh = new THREE.Mesh(geometry, material);
  scene.add(mesh);

  renderer = new THREE.WebGLRenderer({
    antialias: true
  });
  renderer.setSize(600, 400);
  canvas.appendChild(renderer.domElement);

}
