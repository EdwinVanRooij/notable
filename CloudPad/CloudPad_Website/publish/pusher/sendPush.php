<?php
$channel = $_GET['channel'];
$event = $_GET['event'];
$message = $_GET['message'];
$message = $_GET['message'];

require('lib/Pusher.php');

$app_id = '152700';
$app_key = 'dfb1ee7f4027ed8ca2ab';
$app_secret = '794c4715344b5bc7ae36';

$pusher = new Pusher(
  $app_key,
  $app_secret,
  $app_id,
  array('encrypted' => false)
);

$pusher->trigger($channel, $event, $message);

echo $message;

?>