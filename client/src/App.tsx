import React, { useState, useEffect } from "react";
import axios from "axios";

import './App.css'

function App() {
  const [isServerUp, setIsServerUp] = useState<boolean | null>(null); // `null` = unknown, `true` = up, `false` = down
  const [loading, setLoading] = useState<boolean>(true); // To show a loading indicator while checking the server

  useEffect(() => {
    // Function to check server status
    const checkServerStatus = async () => {
      try {
        setLoading(true);
        // Replace "/health" with the endpoint your server exposes for health checks
        const response = await axios.get("http://localhost:8090/actuator/health");
        if (response.status === 200) {
          setIsServerUp(true);
        } else {
          setIsServerUp(false);
        }
      } catch (error) {
        setIsServerUp(false); // Assume the server is down if there's an error
      } finally {
        setLoading(false); // Stop the loading state
      }
    };

    // Initial check when the component mounts
    checkServerStatus();

    // Optionally, check server status every few seconds
    const interval = setInterval(checkServerStatus, 5000); // Check every 5 seconds
    return () => clearInterval(interval); // Cleanup on unmount
  }, []);

  // Render the status
  return (
    <div>
      {loading ? (
        <p>Checking server status...</p>
      ) : isServerUp === null ? (
        <p>Unable to determine server status.</p>
      ) : isServerUp ? (
        <p style={{ color: "green" }}>The server is UP!</p>
      ) : (
        <p style={{ color: "red" }}>The server is DOWN!</p>
      )}
    </div>
  );
};
export default App
