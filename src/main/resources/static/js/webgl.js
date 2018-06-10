var camera, scene, renderer;

function drawCanvas() {
  var canvas = document.getElementById('glCanvas');


  camera = new THREE.PerspectiveCamera(70, 600 / 400, 1, 1000);
  camera.position.z = 150;


  scene = new THREE.Scene();
  scene.background = new THREE.Color(0xe0e0e0);

  var geometry = new THREE.BoxGeometry(100, 100, 100);
  var material = new THREE.MeshBasicMaterial( color: 0x00ff00 } );

  var mesh = new THREE.Mesh(geometry, material);
  scene.add(mesh);

  renderer = new THREE.WebGLRenderer();

  renderer.setSize(600, 400);
  canvas.appendChild(renderer.domElement);


}
