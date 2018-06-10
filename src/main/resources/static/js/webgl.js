var camera, scene, renderer;

function drawCanvas() {
  var canvas = document.getElementById('glCanvas');

  renderer = new THREE.WebGLRenderer({antialias: true});

  renderer.setSize(600, 400);
  canvas.appendChild(renderer.domElement);

  scene = new THREE.Scene();
  scene.background = new THREE.Color(0xe0e0e0);

  camera = new THREE.PerspectiveCamera(40, window.innerWidth / window.innerHeight, 0.2, 1000);
  camera.position.z = 150;
  scene.add(camera);

  var geometry = new THREE.BoxGeometry(100, 100, 100);
  var material = new THREE.MeshBasicMaterial({ color: 0x00ff00 } );

  var mesh = new THREE.Mesh(geometry, material);
  scene.add(mesh);

  renderer.render(scene, camera);

}
