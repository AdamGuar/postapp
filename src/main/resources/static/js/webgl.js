var camera, scene, renderer;

var targetRotation = 0;
var targetRotationOnMouseDown = 0;
var mouseX = 0;
var mouseXOnMouseDown = 0;
var windowHalfX = 600 / 2;
var windowHalfY = 400 / 2;

function drawCanvas() {
  var canvas = document.getElementById('glCanvas');

  renderer = new THREE.WebGLRenderer({
    antialias: true
  });

  renderer.setSize(600, 400);
  canvas.appendChild(renderer.domElement);

  scene = new THREE.Scene();
  scene.background = new THREE.Color(0xFFFFF);

  camera = new THREE.PerspectiveCamera(40, 600 / 400, 1, 1000);
  camera.position.z = 200;

  controls = new THREE.TrackballControls(camera);
  controls.rotateSpeed = 1.0;
  controls.zoomSpeed = 1.2;
  controls.panSpeed = 0.8;
  controls.noZoom = false;
  controls.noPan = false;
  controls.staticMoving = true;
  controls.dynamicDampingFactor = 0.3;
  controls.keys = [65, 83, 68];
  controls.addEventListener('change', render);
  scene.add(camera);


	var light = new THREE.AmbientLight( 0x222222 );
	scene.add( light );


  var geometry = new THREE.BoxGeometry(50, 50, 50);
  var material = new THREE.MeshNormalMaterial({
    color: 0xFF4040
  });

  var mesh = new THREE.Mesh(geometry, material);
  scene.add(mesh);

  render();

}

function render() {
  renderer.render(scene, camera);
}
