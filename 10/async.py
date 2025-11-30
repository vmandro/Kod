import asyncio

async def coroutine1():
    task = asyncio.create_task(coroutine2())
    task
    print(1)

async def coroutine2():
    print(2)
    await asyncio.sleep(1)
   
asyncio.run(coroutine1())
print("finito")
