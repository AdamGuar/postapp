var camera, scene, renderer, controls;

var targetRotation = 0;
var targetRotationOnMouseDown = 0;
var mouseX = 0;
var mouseXOnMouseDown = 0;
var windowHalfX = 600 / 2;
var windowHalfY = 400 / 2;
var model;
function drawCanvas(m) {
  model = m;
  init();
  render();
  animate();
}

function init() {
  var canvas = document.getElementById('glCanvas');

  renderer = new THREE.WebGLRenderer({
    antialias: true
  });

  renderer.setSize(600, 400);
  canvas.appendChild(renderer.domElement);

  scene = new THREE.Scene();
  scene.background = new THREE.Color(0xffffff);

  camera = new THREE.PerspectiveCamera(45, 600 / 400, 1, 10000);
  camera.position.z = 1;

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


  var light = new THREE.AmbientLight(0xffffff);
  var pointLight = new THREE.PointLight( 0xffffff, 0.8 );
  camera.add( pointLight );
  scene.add(light);

  console.log(model);

  for (var el in model.elements) {
    console.log(el.nodes);
    const width = el.nodes[1].x - el.nodes[0].x;
    const height = el.nodes[4].y - el.nodes[0].y;
    const depth = el.nodes[0].z - el.nodes[3].z;

    var geometry = new THREE.BoxGeometry(width, height, depth);
    var material = new THREE.MeshStandardMaterial({color: 0x000000});

    var mesh = new THREE.Mesh(geometry, material);
    mesh.position.set(el.nodes[0].x, el.nodes[0].y, el.nodes[0].z);
    scene.add(mesh);
  }


  render();
}

function render() {
  renderer.render(scene, camera);
}

function animate() {
  requestAnimationFrame(animate);
  controls.update();
}
